PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
ANALYZE sqlite_master;
CREATE TABLE noteTable(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, data TEXT, date TEXT);
DELETE FROM sqlite_sequence;
COMMIT;
