create table players
(
    id         bigint not null auto_increment,
    birth_date date,
    name       varchar(255),
    position   varchar(255),
    team_id    bigint,
    primary key (id)
) engine = InnoDB;

create table teams
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = InnoDB;