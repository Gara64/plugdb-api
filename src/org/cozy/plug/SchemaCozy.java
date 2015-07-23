package org.cozy.plug;
public class SchemaCozy {
	public static final String META =
			"TAB_DESC,6\n"+
			"0	Users	258\n"+
			"1	Docs	258\n"+
			"2	Rules	67\n"+
			"3	LogDeleted	12\n"+
			"4	UpdateLog	512\n"+
			"5	QEP	512\n"+
			"COL_DESC,22\n"+
			"0	0	IdGlobal	4	1	0\n"+
			"1	0	UserID	52	0	4\n"+
			"2	0	UserDesc	202	0	56\n"+
			"3	1	IdGlobal	4	1	0\n"+
			"4	1	DocID	52	0	4\n"+
			"5	1	UserDesc	202	0	56\n"+
			"6	2	IdGlobal	4	1	0\n"+
			"7	2	UserID	4	1	4\n"+
			"8	2	DocID	4	1	8\n"+
			"9	2	Right	3	0	12\n"+
			"10	2	Auth	52	0	15\n"+
			"11	3	TabId	4	1	0\n"+
			"12	3	TuplePos	4	1	4\n"+
			"13	3	Flag	4	1	8\n"+
			"14	4	TabId	4	1	0\n"+
			"15	4	TuplePos	4	1	4\n"+
			"16	4	TupleSize	4	1	8\n"+
			"17	4	CompleteTup	500	0	12\n"+
			"18	5	IdGlobal	4	1	0\n"+
			"19	5	QEPStr	460	0	4\n"+
			"20	5	SQLStr	24	9	464\n"+
			"21	5	ExplainStr	24	9	488\n"+
			"FK_DESC,2\n"+
			"2	7	0	0\n"+
			"2	8	1	3\n"+
			"SKT_DESC,1\n"+
			"0	2	Rules	8\n"+
			"SKT_COL_DESC,2\n"+
			"0	0	0	0	1\n"+
			"0	4	1	3	1\n"+
			"CI_DESC,6\n"+
			"0	0	0	0	1\n"+
			"1	1	1	3	1\n"+
			"2	2	2	6	1\n"+
			"3	3	3	11	1\n"+
			"4	4	4	14	1\n"+
			"5	5	5	18	1\n"+
			"";

}
