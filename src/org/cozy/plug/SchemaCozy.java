package org.cozy.plug;
public class SchemaCozy {
	public static final String META =
			"TAB_DESC,7\n"+
			"0	Users	310\n"+
			"1	Docs	310\n"+
			"2	Shares	258\n"+
			"3	ACL	80\n"+
			"4	LogDeleted	12\n"+
			"5	UpdateLog	512\n"+
			"6	QEP	512\n"+
			"COL_DESC,28\n"+
			"0	0	IdGlobal	4	1	0\n"+
			"1	0	UserID	52	0	4\n"+
			"2	0	SharingRule	52	0	56\n"+
			"3	0	UserParam	202	0	108\n"+
			"4	1	IdGlobal	4	1	0\n"+
			"5	1	DocID	52	0	4\n"+
			"6	1	SharingRule	52	0	56\n"+
			"7	1	UserParam	202	0	108\n"+
			"8	2	IdGlobal	4	1	0\n"+
			"9	2	ShareID	52	0	4\n"+
			"10	2	Desc	202	0	56\n"+
			"11	3	IdGlobal	4	1	0\n"+
			"12	3	Share	4	1	4\n"+
			"13	3	User	4	1	8\n"+
			"14	3	Doc	4	1	12\n"+
			"15	3	Right	12	0	16\n"+
			"16	3	Auth	52	0	28\n"+
			"17	4	TabId	4	1	0\n"+
			"18	4	TuplePos	4	1	4\n"+
			"19	4	Flag	4	1	8\n"+
			"20	5	TabId	4	1	0\n"+
			"21	5	TuplePos	4	1	4\n"+
			"22	5	TupleSize	4	1	8\n"+
			"23	5	CompleteTup	500	0	12\n"+
			"24	6	IdGlobal	4	1	0\n"+
			"25	6	QEPStr	460	0	4\n"+
			"26	6	SQLStr	24	9	464\n"+
			"27	6	ExplainStr	24	9	488\n"+
			"FK_DESC,3\n"+
			"3	12	2	8\n"+
			"3	13	0	0\n"+
			"3	14	1	4\n"+
			"SKT_DESC,1\n"+
			"0	3	ACL	12\n"+
			"SKT_COL_DESC,3\n"+
			"0	0	1	4	1\n"+
			"0	4	0	0	1\n"+
			"0	8	2	8	1\n"+
			"CI_DESC,5\n"+
			"0	0	0	0	1\n"+
			"1	1	1	4	1\n"+
			"2	2	2	8	1\n"+
			"3	3	3	11	1\n"+
			"4	6	6	24	1\n"+
			"";

}
