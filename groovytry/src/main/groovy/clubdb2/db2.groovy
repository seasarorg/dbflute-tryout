
import groovy.sql.Sql

sql=Sql.newInstance("jdbc:db2://localhost:50002/dfexdb", "db2inst1", "********", "com.ibm.db2.jcc.DB2Driver")
sql.execute("SET CURRENT SCHEMA = DFEXDB")

println "- - - - - - - - - - - - - - - - "
sql.eachRow("select * from MEMBER", {println it.MEMBER_ID + " : " + it.MEMBER_NAME})

println "- - - - - - - - - - - - - - - - "
memberList = sql.rows("select * from MEMBER")
memberList.each {row-> println row.MEMBER_NAME + " : " +row.BIRTHDATE}

println "- - - - - - - - - - - - - - - - "
sql.withTransaction {
  sql.eachRow("select * from MEMBER where MEMBER_NAME like ?", ['S%']) {println it.MEMBER_ID + " : " + it.MEMBER_NAME}
}

sql.close()
