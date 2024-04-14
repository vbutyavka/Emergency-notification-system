CREATE TYPE recipient_statuses as ENUM ('ONLINE','OFFLINE');
CREATE TYPE mail_statuses as ENUM ('CREATED','SENT');

CREATE TABLE Sender
(
    id_sender bigint NOT NULL PRIMARY KEY,
    login varchar(25) NOT NULL,
    pword varchar(255) NOT NULL,
    CONSTRAINT login_unique UNIQUE (login)
);

CREATE TABLE Mailing
(
    id_mailing bigint NOT NULL PRIMARY KEY,
    mail_text text NOT NULL,
    fk_id_sender bigint NOT NULL REFERENCES Sender(id_sender) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Federal_District
(
    id_fd int NOT NULL PRIMARY KEY,
    fd_name varchar(255) NOT NULL,
    CONSTRAINT fd_name_unique UNIQUE (fd_name)
);

CREATE TABLE Region
(
    id_region int NOT NULL PRIMARY KEY,
    region_name varchar(255) NOT NULL,
    fk_id_fd int NOT NULL REFERENCES Federal_District(id_fd) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT region_name_unique UNIQUE (region_name)
);

CREATE TABLE Local_District
(
    id_ld int NOT NULL PRIMARY KEY,
    ld_name varchar(255) NOT NULL,
    fk_id_region int NOT NULL REFERENCES Region(id_region) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Recipient
(
    id_recipient bigint NOT NULL PRIMARY KEY,
    phone_number varchar(11) NOT NULL,
    recipient_status recipient_statuses,
    fk_id_ld int NOT NULL REFERENCES Local_District(id_ld) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT phone_number_unique UNIQUE (phone_number)
);

CREATE TABLE Mail
(
    id_mail bigint NOT NULL PRIMARY KEY,
    fk_id_recipient bigint NOT NULL REFERENCES Recipient(id_recipient) ON DELETE CASCADE ON UPDATE CASCADE,
    fk_id_mailing bigint NOT NULL REFERENCES Mailing(id_mailing) ON DELETE CASCADE ON UPDATE CASCADE,
    mail_status mail_statuses
);