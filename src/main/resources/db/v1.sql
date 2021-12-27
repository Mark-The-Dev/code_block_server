-- Creates users table, default means value if none declared
CREATE TABLE users
(
    id serial PRIMARY KEY,
    email text unique NOT NULL,
    password text NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(69) NOT NULL
);
-- SQL to make tech topics
CREATE TABLE tech_topics
(
    id serial PRIMARY KEY,
    topic_name VARCHAR(60) NOT NULL,
    topic_info text NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Creates topic articles, Foreign Key indicated by REFERENCES
CREATE TABLE topic_articles
(
    id serial PRIMARY KEY,
    tech_topic_id serial REFERENCES tech_topics (id),
    title VARCHAR(80) NOT NULL,
    post_body text NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    author serial REFERENCES users (id)
);

CREATE TABLE topic_subscriptions
(
    id serial PRIMARY KEY,
    tech_topic_id serial REFERENCES tech_topics (id),
    user_id serial REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE neighbors
(
    id serial PRIMARY KEY,
    user_id serial REFERENCES users (id),
    neighbor_id serial REFERENCES users (id),
    status VARCHAR(60) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE chat_rooms
(
    id serial PRIMARY KEY,
    room_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE chat_participants
(
    id serial PRIMARY KEY,
    chat_room_id serial REFERENCES chat_rooms (id),
    user_id serial REFERENCES users (id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    unread_at TIMESTAMP
);

CREATE TABLE chat_messages
(
    id serial PRIMARY KEY,
    chat_room_id serial references chat_rooms(id),
    user_id serial REFERENCES users (id),
    message text NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- INPUT Seeded data

