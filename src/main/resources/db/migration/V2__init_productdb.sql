create table if not exists products(
    id bigint generated by default as identity primary key ,
    user_id bigint references users(id),
    title varchar(100) not null ,
    description varchar(5000) ,
    price bigint not null ,
    category varchar not null
)