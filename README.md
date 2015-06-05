# plugdb-api
Minimal and very basic API to connect PlugDB. Only for testing purposes.

## Prerequisite
You need a working PlugDB running on a virtual serial port to use it.
Generally /dev/ACM0 on Linux and COM1 on Windows 

## Use it
Import the .jar in your project if you need it as it is.



The public functions are : 
* plugInit(String serialPort)
* plugInsert(List<String> docIds)
* plugSelect()
* plugClose()
* plugReset()
* plugFPAuthentication()

## Hack it

The database schema can be changed by generating another QEPCozy.java  
The associated queries can be added in Tools_dmsp.java and called in Queries.java

The project is designed to work with the JDBC_API project
