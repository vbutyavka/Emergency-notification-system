# Используем официальный образ PostgreSQL 14
FROM postgres:14

# Установка переменных окружения
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=diplomaMaster

# Пробрасываем порт 5432
EXPOSE 5432

# Копируем SQL скрипт для инициализации мастера
COPY src/main/resources/scripts/init-master.sql /docker-entrypoint-initdb.d/

# Устанавливаем права доступа для скрипта
RUN chmod 777 /docker-entrypoint-initdb.d/init-master.sql
