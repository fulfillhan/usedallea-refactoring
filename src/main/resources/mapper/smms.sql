use smms;
    CREATE TABLE USER  (
           USER_ID 	   BIGINT	    AUTO_INCREMENT PRIMARY KEY ,
           NICKNAME        VARCHAR(50),
           PASSWORD 	   VARCHAR(200)	 NOT NULL,
           ROLE           VARCHAR(10)   NOT NULL,
           NAME 		   VARCHAR(100)	 NOT NULL,
           ACTIVE_YN	   CHAR(1)	     DEFAULT "y",
           EMAIL 	       VARCHAR(1000) NOT NULL,
           EMAILSTS_YN    CHAR(1)       NOT NULL,
           PHONE_NUMBER  VARCHAR(20)   NOT NULL,
           SMSSTS_YN      CHAR(1)       NOT NULL,
           UPDATED_AT     TIMESTAMP     DEFAULT NOW(),
           CREATED_AT     TIMESTAMP     DEFAULT NOW(),
           ROAD_ADDRESS   VARCHAR(500),
           JIBUN_ADDRESS  VARCHAR(500),
           NAMUJI_ADDRESS VARCHAR(500),
           ZIP_CODE        VARCHAR(50)
    );

	CREATE TABLE PRODUCT(
		PRODUCT_ID         BIGINT        AUTO_INCREMENT PRIMARY KEY,
        SELLER_ID          VARCHAR(50)   NOT NULL,
        IMG_ID             BIGINT        NOT NULL,
        TITLE              VARCHAR(100)   NOT NULL,
		PRICE              VARCHAR(10)    NOT NULL,
		DESCRIPTION        VARCHAR(1000) NOT NULL,
		QUALITY_CONDITION  VARCHAR(200)  NOT NULL,
		CATEGORY           VARCHAR(100)  NOT NULL,
		CREATED_AT         TIMESTAMP     DEFAULT NOW(),
		UPDATED_AT         TIMESTAMP     DEFAULT NOW(),
		READ_COUNT         INT           DEFAULT 0,
        STATUS             VARCHAR(100)  NOT NULL,
        FOREIGN KEY (SELLER_ID) REFERENCES USER(USER_ID),


CREATE TABLE REVIEW(
	REVIEW_ID     BIGINT         AUTO_INCREMENT PRIMARY KEY,
	PURCHASER_ID  BIGINT         NOT NULL,   
	COMMENTS      VARCHAR(2000)  NOT NULL,
	UPDATED_AT    TIMESTAMP      DEFAULT NOW(),
	CREATED_AT    TIMESTAMP      DEFAULT NOW(),
	HELPFUL_COUNT  INT           DEFAULT 0,
	FOREIGN KEY (PURCHASER_ID) REFERENCES USER(USER_ID)
);

	CREATE TABLE IMG(
			IMG_ID        BIGINT      DEFAULT 0,
			IMG_SEQ       BIGINT      DEFAULT 0,
			IMG_UUID      VARCHAR(200) NOT NULL,
			ORIGINAL_NAME VARCHAR(200) NOT NULL,
			UPDATED_AT    TIMESTAMP    DEFAULT NOW(),
			CREATE_AT     TIMESTAMP    DEFAULT NOW(),
			PRIMARY KEY (IMG_ID, IMG_SEQ)
		);

    CREATE TABLE QA(
        QA_ID      BIGINT       AUTO_INCREMENT PRIMARY KEY,
        PARENT_ID  BIGINT       NOT NULL,
        TYPE       VARCHAR(100) NOT NULL,
        PRODUCT_ID BIGINT       NOT NULL,
        USER_ID    VARCHAR(50)  NOT NULL,
        UPDATED_AT TIMESTAMP    DEFAULT NOW(),
        CREATED_AT TIMESTAMP    DEFAULT NOW(),
        CONTENTS   VARCHAR(2000) NOT NULL,
        STATUS     VARCHAR(100)  NOT NULL,
        PRIVATE_YN CHAR(1)       DEFAULT "Y",
        DELETE_YN  CHAR(1)       DEFAULT "N",
        PASSWORD   VARCHAR(200)   NOT NULL
    );

	CREATE TABLE NOTICE( 
	NOTICE_ID   BIGINT    AUTO_INCREMENT PRIMARY KEY,
	USER_ID     VARCHAR(50)   NOT NULL, 
	TITLE       VARCHAR(300)  NOT NULL,
	CONTENTS    VARCHAR(2000) NOT NULL,
	UPDATED_AT  TIMESTAMP     DEFAULT NOW(),
	CREATED_AT  TIMESTAMP     DEFAULT NOW(),
	READ_COUNT  INT           DEFAULT 0,
	DELETE_YN   CHAR(1)       DEFAULT "N"
	);

	CREATE TABLE ZZIM(
	ZZIM_ID    BIGINT      AUTO_INCREMENT PRIMARY KEY,
	CREATED_AT TIMESTAMP   DEFAULT NOW(),
	USER_ID    VARCHAR(50) NOT NULL, 
	PRODUCT_ID BIGINT      NOT NULL
	);

	CREATE TABLE LIKES(
	LIKES_ID      BIGINT    AUTO_INCREMENT PRIMARY KEY,
	CREATED_AT   TIMESTAMP DEFAULT NOW(),
	PURCHASER_ID BIGINT    NOT NULL,
	SELLER_ID    BIGINT    NOT NULL
	);
    
CREATE TABLE MANAGER(
	MANAGER_ID VARCHAR(50) NOT NULL, 
	NOTICE_ID BIGINT,
	PASSWORD   VARCHAR(200) NOT NULL
    );