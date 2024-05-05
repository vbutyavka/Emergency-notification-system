CREATE TABLE Sender
(
    id BIGSERIAL PRIMARY KEY,
    login varchar(25) NOT NULL,
    pword varchar(255) NOT NULL,
    CONSTRAINT login_unique UNIQUE (login)
);
CREATE TABLE Mailing
(
    id BIGSERIAL PRIMARY KEY,
    mail_text text NOT NULL,
    fk_id_sender bigint NOT NULL REFERENCES Sender(id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Federal_District
(
    id BIGSERIAL PRIMARY KEY,
    fd_name varchar(255) NOT NULL,
    CONSTRAINT fd_name_unique UNIQUE (fd_name)
);
CREATE TABLE Region
(
    id BIGSERIAL PRIMARY KEY,
    region_name varchar(255) NOT NULL,
    fk_id_fd int NOT NULL REFERENCES Federal_District(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT region_name_unique UNIQUE (region_name)
);
CREATE TABLE Local_District
(
    id BIGSERIAL PRIMARY KEY,
    ld_name varchar(255) NOT NULL,
    fk_id_region int NOT NULL REFERENCES Region(id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Recipient
(
    id BIGSERIAL PRIMARY KEY,
    phone_number varchar(11) NOT NULL,
    recipient_status varchar(32) NOT NULL,
    fk_id_ld int NOT NULL REFERENCES Local_District(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT phone_number_unique UNIQUE (phone_number)
);
CREATE TABLE Mail
(
    id BIGSERIAL PRIMARY KEY,
    fk_id_recipient bigint NOT NULL REFERENCES Recipient(id) ON DELETE CASCADE ON UPDATE CASCADE,
    fk_id_mailing bigint NOT NULL REFERENCES Mailing(id) ON DELETE CASCADE ON UPDATE CASCADE,
    mail_status varchar(32) NOT NULL
);
CREATE INDEX idx_fkIdLd ON Recipient (fk_id_ld);
CREATE INDEX idx_fkIdRegion ON Local_District (fk_id_region);
CREATE INDEX idx_fkIdFd ON Region (fk_id_fd);
CREATE TABLE Mail_History
(
    id BIGSERIAL PRIMARY KEY,
    recipient_phone varchar(11) NOT NULL,
    id_mailing bigint NOT NULL,
    mail_status varchar(32) NOT NULL
);
ALTER TABLE Mail ADD CONSTRAINT mailing_recipient_uniq_on_mail UNIQUE (fk_id_mailing, fk_id_recipient);
ALTER TABLE Mail_History ADD CONSTRAINT mailing_recipient_uniq_on_mail_history UNIQUE (id_mailing, recipient_phone);
CREATE INDEX idx_mail_on_recipient_mailing ON Mail(fk_id_recipient, fk_id_mailing);
CREATE INDEX idx_mailing_on_sender ON Mailing(fk_id_sender);
CREATE INDEX idx_mail_history_on_recipient_mailing ON Mail_History(recipient_phone, id_mailing);
CREATE INDEX idx_recipient_on_local_district ON Recipient(fk_id_ld);
CREATE INDEX idx_local_district_on_region ON Local_District(fk_id_region);
CREATE INDEX idx_region_on_federal_district ON Region(fk_id_fd);
CREATE INDEX idx_sender_on_login ON Sender(login);
CREATE TABLE Sms_Json
(
    id BIGSERIAL PRIMARY KEY,
    mail_id bigint NOT NULL,
    address varchar(11) NOT NULL,
    sms_text text NOT NULL
);