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


-- Input initial seeded table data.
INSERT INTO users (id, email, password, first_name, last_name)
VALUES
(1, 'swifttactics@gmail.com', '$2b$10$25U4.TNlydosWSx8zZBx6eT/yO3sBccYxpGNbE2ouhEm7sbgxteZu', 'Mark', 'Marcello'),
(2, 'alifastith@gmail.com', '$2b$10$25U4.TNlydosWSx8zZBx6eT/yO3sBccYxpGNbE2ouhEm7sbgxteZu', 'Alia', 'Stith');


INSERT INTO tech_topics (id, topic_name, topic_info) VALUES
(1, 'C++', 'This is a fun language that everyone should learn'),
(2, 'Java', 'Arguably the best programming language ever created, If you know this language you will be a strong programmer.'),
(3, 'JavaScript', 'This language is okay.');

INSERT INTO topic_articles (id, tech_topic_id, author, title, post_body) VALUES
(1, 1, 2, 'C++ is awesome!', 'I would use this language everyday if I could'),
(2, 2, 1, 'One Reason To Always Use Java!', 'Java is the best language!'),
(3, 3, 1, 'JavaScript is Funny?', 'One JavaScript walked into the bar and asked the server for a drink.' ||
                               ' When the server asked "what type?" JavaScript replied "It does not matter!"');

INSERT INTO topic_subscriptions (id, tech_topic_id, user_id) VALUES
(1, 1, 2),
(2, 2, 1);

INSERT INTO chat_rooms (id, room_name) VALUES
(1, 'Mark-and-Ali Powwow');

INSERT INTO chat_participants (id, chat_room_id, user_id) VALUES
(1, 1, 1),
(2, 1, 2);

INSERT INTO chat_messages (id, chat_room_id, user_id, message) VALUES
(1, 1, 1, 'Hi there!'),
(2, 1, 2, 'I like C++');

INSERT INTO neighbors (id, user_id, neighbor_id, status) VALUES
(1, 1, 2, 'Friends'),
(2, 2, 1, 'Friends');
