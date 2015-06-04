# plugdb-api
Minimal and very basic API to connect PlugDB. Only for testing purposes.

# Prerequisite
You need a working PlugDB running on a virtual serial port to use it

# Use it
Import the .jar in your project if you need it as it is.

The database schema can be changed by generating another QEPCozy.java
The associated queries can be added in Tools_dmsp.java and called in Queries.java

The public functions are : 
* plugInit(String port)
* plugInsert(List<String> docIds)
* plugSelect()
* plugClose()
* plugReset()


