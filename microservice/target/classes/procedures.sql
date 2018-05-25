CREATE PROCEDURE find_all_users
  AS
  BEGIN
    SELECT * FROM user_tbl
  END
GO

CREATE PROCEDURE insert_new_user (@Name VARCHAR(50), @Age INTEGER)
  AS
  BEGIN
    INSERT INTO user_tbl
    VALUES (@Age,@Name)
  END
GO
