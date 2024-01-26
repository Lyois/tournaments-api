CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    team_id TEXT,
    lane TEXT,
    name TEXT,
    kda DOUBLE PRECISION,
    kp DOUBLE PRECISION,
    vspm DOUBLE PRECISION,
    dmg DOUBLE PRECISION,
    gold DOUBLE PRECISION
);