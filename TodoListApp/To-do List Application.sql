-- todoList --
CREATE TABLE "TESTDB"."TODOLIST" 
   (	"ID" NUMBER(*,0), 
	"DESCRIPTION" VARCHAR2(300 BYTE), 
	"DATECOMPLETED" DATE, 
	"DUEDATE" DATE, 
	"STATUSID" NUMBER(*,0), 
	"LISTPRIORITY" NVARCHAR2(20), 
	"USERID" NUMBER(*,0), 
	 PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "FK_USERID" FOREIGN KEY ("USERID")
	  REFERENCES "TESTDB"."TODOUSER" ("ID") ENABLE, 
	 CONSTRAINT "FK_STATUSID" FOREIGN KEY ("STATUSID")
	  REFERENCES "TESTDB"."TODOSTATUS" ("ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE OR REPLACE TRIGGER "TESTDB"."TODOLIST_TRG" 
BEFORE INSERT ON TODOLIST 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT TODOLIST_SEQ1.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."TODOLIST_TRG" ENABLE;

-- todoStatus --
CREATE TABLE "TESTDB"."TODOSTATUS" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(50 BYTE), 
	 PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE OR REPLACE TRIGGER "TESTDB"."TODOSTATUS_TRG" 
BEFORE INSERT ON TODOSTATUS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."TODOSTATUS_TRG" ENABLE;

-- todoUser --
CREATE TABLE "TESTDB"."TODOUSER" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(50 BYTE), 
	"PASSWORD" VARCHAR2(20 BYTE), 
	 PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE OR REPLACE TRIGGER "TESTDB"."TODOUSER_TRG" 
BEFORE INSERT ON TODOUSER 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT TODOUSER_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."TODOUSER_TRG" ENABLE;