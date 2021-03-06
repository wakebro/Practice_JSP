--getNoticeList
SELECT ROWNUM, NOTICE.* FROM notice ORDER BY CREATE_DATE DESC;

CREATE VIEW NOTICE_VIEW AS
SELECT N.NO, N.TITLE, N.ID, N.CREATE_DATE, N.FILES,N.HIT, COUNT(C.ID) CMT_COUNT 
    FROM NOTICE N 
    LEFT JOIN "N_COMMENT" C ON N.NO=C.NOTICE_ID
    GROUP BY N.NO, N.TITLE, N.ID, N.CREATE_DATE, N.FILES, N.HIT;
   -- ORDER BY N.CREATE_DATE DESC;

CREATE USER mytest IDENTIFIED BY mytest;
GRANT CREATE VIEW TO MYTEST;

SELECT * FROM (
    SELECT ROWNUM NUM, N.* FROM (
        SELECT * FROM NOTICE_VIEW WHERE TITLE LIKE '%%' ORDER BY CREATE_DATE DESC
    ) N
) WHERE NUM BETWEEN 6 AND 10;



--getNoticeCount
SELECT COUNT(*) FROM NOTICE;

--getNotice
SELECT * FROM NOTICE WHERE NO=4;

--getNextNotice
SELECT * FROM NOTICE WHERE NO=(
    SELECT NO FROM NOTICE WHERE CREATE_DATE>(
        SELECT CREATE_DATE FROM NOTICE WHERE NO=3) AND ROWNUM = 1);

--getPrevNotice
SELECT * FROM NOTICE WHERE NO=(
    SELECT NO FROM (SELECT * FROM NOTICE ORDER BY CREATE_DATE DESC)
        WHERE CREATE_DATE<(SELECT CREATE_DATE FROM NOTICE WHERE NO=3)
        AND ROWNUM = 1);
SELECT NO FROM (SELECT * FROM NOTICE ORDER BY CREATE_DATE DESC)
        WHERE CREATE_DATE<(SELECT CREATE_DATE FROM NOTICE WHERE NO=3)
