# NWT_Tim2

Travel review

<h3>Local environment setup guide</h3>
<ul>
  <li>Initilize project/microservice</li>
  <li>Right click on pom.xml -> (Re)Import</li>
  <li>Install MySql locally</li>
  <li>Create databases for each microservice that you are working on: e.g. nwt2_review (Review DB)</li>
  <li>Setup the <code>application.properties</code> with the code showed below</li>
</ul>

<code>
spring.jpa.hibernate.ddl-auto=create // this will create new database and flush all the data on project startup<br />
spring.datasource.url=jdbc:mysql://localhost/nwt2_MSNAME <br />
spring.datasource.username=DB_USER<br />
spring.datasource.password=DB_PASSWORD<br />
spring.datasource.driverClassName=com.mysql.jdbc.Driver  
</code>

<h5>Variables</h5>

MSNAME => microservice name<br />
DB_USER => your local database user (if not setup anything, use <i>root</i><br />
DB_PASSWORD => local database password (default for <i>root</li> is an empty string<br />

<h5>How to check if it is working?</h5>

After running a microsrvice, open the database (e.g. via PHPMyAdmin) and see if the datbase has the created tables.

<hr />
