package org.cozy.plug;
public class QEPCozy {
public static final String META =
	"TAB_DESC,5\n"+
	"0	Users	56\n"+
	"1	Docs	56\n"+
	"2	Rules	67\n"+
	"3	LogDeleted	12\n"+
	"4	UpdateLog	514\n"+
	"COL_DESC,16\n"+
	"0	0	IdGlobal	4	1	0\n"+
	"1	0	UserID	52	0	4\n"+
	"2	1	IdGlobal	4	1	0\n"+
	"3	1	DocID	52	0	4\n"+
	"4	2	IdGlobal	4	1	0\n"+
	"5	2	UserID	4	1	4\n"+
	"6	2	DocID	4	1	8\n"+
	"7	2	Right	3	0	12\n"+
	"8	2	Auth	52	0	15\n"+
	"9	3	TabId	4	1	0\n"+
	"10	3	TuplePos	4	1	4\n"+
	"11	3	Flag	4	1	8\n"+
	"12	4	TabId	4	1	0\n"+
	"13	4	TuplePos	4	1	4\n"+
	"14	4	TupleSize	4	1	8\n"+
	"15	4	CompleteTup	502	0	12\n"+
	"FK_DESC,2\n"+
	"2	5	0	0\n"+
	"2	6	1	2\n"+
	"SKT_DESC,1\n"+
	"0	2	Rules	8\n"+
	"SKT_COL_DESC,2\n"+
	"0	0	0	0	1\n"+
	"0	4	1	2	1\n"+
	"CI_DESC,5\n"+
	"0	0	0	0	1\n"+
	"1	1	1	2	1\n"+
	"2	2	2	4	1\n"+
	"3	3	3	9	1\n"+
	"4	4	4	12	1\n"+
	"";

public static String EP_RULE_INSERT =
	"/*EP \u0004 2 4 4 -1 0 ?1 # 2 5 5 -1 1 ?2 # 7 3 3 4 5 # 6 2 2 3 2 v30 # 5 1 1 2 2 0 0 0 r1 1 r3 # 5 0 0 1 5 2 1 5 ?1 6 ?2 7 ?3 8 ?4 4 r4 # \u0000*/";

public static String EP_USER_INSERT =
	"/*EP \u0001 6 1 1 -1 0 v30 # 5 0 0 1 2 0 1 1 ?1 0 r0 # \u0000*/";

public static String EP_DOC_INSERT =
	"/*EP \u0001 6 1 1 -1 1 v30 # 5 0 0 1 2 1 1 3 ?1 2 r0 # \u0000*/";

public static String EP_SELECT_STAR_BY_USERRULE =
	"/*EP \u0001 0 2 2 2 # 1 1 1 2 r0 5 2 1 4 5 6 7 8 # 4 0 0 1 5 0 ?1 r2 # \u0000 5 1 1 IdGlobal 1 2 UserID 1 3 DocID 0 4 Right 0 5 Auth # \u0000*/";

public static String EP_SELECT_STAR_RULES =
	"/*EP \u0000 0 1 1 2 # 1 0 0 1 r0 5 2 1 4 5 6 7 8 # \u0000 5 1 1 IdGlobal 1 2 UserID 1 3 DocID 0 4 Right 0 5 Auth # \u0000*/";

public static String EP_SELECT_STAR_USERS =
	"/*EP \u0000 0 1 1 0 # 1 0 0 1 r0 2 0 1 0 1 # \u0000 2 1 1 IdGlobal 0 2 UserID # \u0000*/";

public static String EP_SELECT_STAR_DOCS =
	"/*EP \u0000 0 1 1 1 # 1 0 0 1 r0 2 1 1 2 3 # \u0000 2 1 1 IdGlobal 0 2 DocID # \u0000*/";

public static String EP_DOCID_BY_USERID =
	"/*EP \u0001 0 4 4 2 # 1 3 3 4 r0 2 0 0 1 0 # 1 2 2 3 r2 1 0 1 1 # 4 1 1 2 1 0 ?1 r3 # 1 0 0 1 r1 1 1 1 3 # \u0000 1 0 5 DocID # \u0000*/";

public static String EP_RULES_BY_USERID =
	"/*EP \u0001 0 5 5 2 # 1 4 4 5 r0 2 0 0 1 0 # 1 3 3 4 r2 1 0 1 1 # 4 2 2 3 1 0 ?1 r3 # 1 1 1 2 r1 1 1 1 3 # 1 0 0 1 r0 2 2 1 7 8 # \u0000 3 0 5 DocID 0 6 right 0 7 auth # \u0000*/";

}

