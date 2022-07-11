

def crate_db():
    scheme = f"create database triple; "
    create_user = f"create user triple_user@'%' identified by 'triple_234@';"
    grant_privilage = f"grant select, insert, update, delete, alter, create, drop, index on triple.* to triple_user@'%';"

    query_list = [scheme, create_user, grant_privilage]
    for query in query_list:
        print(query)


def create_tb():
    events = f"CREATE TABLE IF NOT EXISTS TB_EVENTS (" \
             f"    ID BIGINT NOT NULL AUTO_INCREMENT," \
             f"    TYPE VARCHAR(30) NOT NULL," \
             f"    ACTION VARCHAR(30) NOT NULL," \
             f"    REVIEW_ID VARCHAR(50) NOT NULL," \
             f"    CONTENT VARCHAR(200) NOT NULL," \
             f"    ATTACHED_PHOTO_IDS VARCHAR(100) NOT NULL," \
             f"    USER_ID VARCHAR(50) NOT NULL," \
             f"    PLACE_ID VARCHAR(50) NOT NULL," \
             f"    CREATED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," \
             f"    MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," \
             f"    PRIMARY KEY(ID) " \
             f") DEFAULT COLLATE=utf8mb4_general_ci CHARSET=utf8mb4 ;"

    reviews = f"CREATE TABLE IF NOT EXISTS TB_REVIEWS (" \
              f"    ID BIGINT NOT NULL AUTO_INCREMENT," \
              f"    REVIEW_ID VARCHAR(50) NOT NULL," \
              f"    CONTENT VARCHAR(200) NOT NULL," \
              f"    ATTACHED_PHOTO_IDS VARCHAR(100) NOT NULL," \
              f"    USER_ID VARCHAR(50) NOT NULL," \
              f"    PLACE_ID VARCHAR(50) NOT NULL," \
              f"    CREATED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," \
              f"    MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," \
              f"    PRIMARY KEY(ID) " \
              f") DEFAULT COLLATE=utf8mb4_general_ci CHARSET=utf8mb4 ;"

    points = f"CREATE TABLE IF NOT EXISTS TB_POINTS (" \
             f"    ID BIGINT NOT NULL AUTO_INCREMENT," \
             f"    USER_ID VARCHAR(50) NOT NULL," \
             f"    REVIEW_ID VARCHAR(50) NOT NULL," \
             f"    PLACE_ID VARCHAR(50) NOT NULL,    " \
             f"    POINT int unsigned NOT NULL," \
             f"    CREATED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " \
             f"    MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  " \
             f"    PRIMARY KEY(ID) )" \
             f"    DEFAULT COLLATE=utf8mb4_general_ci CHARSET=utf8mb4 ;"

    query_list = [events, reviews, points]
    for query in query_list:
        print(query)


crate_db()
print("======================================")
create_tb()
