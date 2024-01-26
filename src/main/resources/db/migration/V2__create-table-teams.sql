CREATE TABLE teams(
    id SERIAL PRIMARY KEY,
    tournament_id TEXT,
    team_id TEXT,
    name TEXT,
    region TEXT,
    win INTEGER,
    lose INTEGER,
    win_percent DOUBLE PRECISION,
    game_duration DOUBLE PRECISION,
    url_icon TEXT
);