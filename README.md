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
spring.jpa.hibernate.ddl-auto=create 
</code>

<code>
spring.datasource.url=jdbc:mysql://localhost/nwt2_MSNAME
</code>

<code>
spring.datasource.username=DB_USER
</code>

<code>
spring.datasource.password=DB_PASSWORD
</code>

<code>
spring.datasource.driverClassName=com.mysql.jdbc.Driver  
</code>

<h5>Variables</h5>

<code>
MSNAME => microservice name
</code>

<code>
DB_USER => your local database user (if not setup anything, use <i>root</i>
</code>

<code>
DB_PASSWORD => local database password (default for <i>root</li> is an empty string
</code>

<h5>How to check if it is working?</h5>

After running a microsrvice, open the database (e.g. via PHPMyAdmin) and see if the datbase has the created tables.

<hr />
