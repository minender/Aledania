FROM debian:stretch

RUN apt-get update
RUN apt-get install postgresql-9.6 -y && rm -rf /var/lib/apt/lists/*
RUN sed -i 's/local   all             postgres                                peer/local   all             postgres                                ident/g'  /etc/postgresql/9.6/main/pg_hba.conf
RUN sed -i 's/local   all             all                                     peer/local   all             all                                     md5/g' /etc/postgresql/9.6/main/pg_hba.conf
RUN sed -i 's/host    all             all             127.0.0.1\/32            md5/host    all             all             172.16.0.0\/14            md5/g' /etc/postgresql/9.6/main/pg_hba.conf
RUN sed -i "s/#listen_addresses = 'localhost'/listen_addresses = '*'/g" /etc/postgresql/9.6/main/postgresql.conf

COPY aledania.sql /home

USER postgres

RUN /etc/init.d/postgresql start &&\ 
    psql --command "CREATE USER userdb WITH PASSWORD 'password';" &&\
    psql --command "CREATE USER fedra WITH PASSWORD 'password';" &&\
    psql --command "CREATE DATABASE namedb OWNER userdb;" &&\
    psql --command "CREATE DATABASE namedb OWNER userdb;" &&\
    psql --command "\i /home/aledania.sql;" aledania &&\
    /etc/init.d/postgresql stop

EXPOSE 5432

#VOLUME  ["/etc/postgresql", "/var/log/postgresql", "/var/lib/postgresql"]

CMD ["/usr/lib/postgresql/9.6/bin/postgres", "-D", "/var/lib/postgresql/9.6/main/", "-c", "config_file=/etc/postgresql/9.6/main/postgresql.conf"]

