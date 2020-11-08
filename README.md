# DbItil002
Maven Project - JDBC functional testing with JUnit.

<p class="has-line-data" data-line-start="0" data-line-end="1">Console Output:</p>
<p class="has-line-data" data-line-start="2" data-line-end="5">Functional test started for -&gt; com.romel.DbUtil<br>
Data Source -&gt; jdbc:mysql://localhost:3306/classicmodels2<br>
Run Date and Time -&gt; 08/11/2020 06:51:04</p>
<hr>
<p class="has-line-data" data-line-start="6" data-line-end="10">Method under test -&gt; public void connect()<br>
Query String: None<br>
Commments: Connects to the MySQL local database.<br>
Result:</p>
<h2 class="code-line" data-line-start=11 data-line-end=13 ><a id="Successfull_11"></a>Test Successful.</h2>
<p class="has-line-data" data-line-start="13" data-line-end="17">Method under test -&gt; public ResultSet getData(String sql, String dataType, String argument<br>
Query String: Select * From employees Where employeeNumber = 1803<br>
Comments: Checks if the database already has a record for employee number 1803.<br>
Result:</p>
<p class="has-line-data" data-line-start="18" data-line-end="20">Test data does not exists in the database.<br>
Test Successful.</p>
<hr>
<p class="has-line-data" data-line-start="21" data-line-end="25">Method under test -&gt; public ResultSet getData(String sql)<br>
Query String: Select * From employees Limit 5<br>
Comments: Retrieves the first five employee records.<br>
Result:</p>
<h2 class="code-line" data-line-start=26 data-line-end=28 ><a id="Successfull_26"></a>Test Successful.</h2>
<p class="has-line-data" data-line-start="28" data-line-end="33">Method under test -&gt; public ResultSet getData(String sql, String dataType, String argument)<br>
Query String: select employeeNumber, concat(firstName, &quot; &quot;, lastName) as “Name”, city from employees<br>
left join offices on employees.officeCode = offices.officeCode where employeeNumber &gt; 1500<br>
Comments: Retrieves the name and city of employees where the employee number is greater than 1500.<br>
Result:</p>
<p class="has-line-data" data-line-start="34" data-line-end="44">employeeNumber                     Name                               city<br>
1501                               Larry Bott                         London<br>
1504                               Barry Jones                        London<br>
1611                               Andy Fixter                        Sydney<br>
1612                               Peter Marsh                        Sydney<br>
1619                               Tom King                           Sydney<br>
1621                               Mami Nishi                         Tokyo<br>
1625                               Yoshimi Kato                       Tokyo<br>
1702                               Martin Gerard                      Paris<br>
Test Successful.</p>
<hr>
<p class="has-line-data" data-line-start="45" data-line-end="49">Method under test -&gt; public ResultSet getData(String sql)<br>
Query String: Select Count(employeeNumber) From employees<br>
Comments: Retrieves the total number of records in the employees table.<br>
Result:</p>
<p class="has-line-data" data-line-start="50" data-line-end="53">Count(employeeNumber)<br>
23<br>
Test Successful.</p>
<hr>
<p class="has-line-data" data-line-start="54" data-line-end="58">Method under test -&gt; public int updateData(String sql, String[] dataType, String[] argument)<br>
Query String: Insert Into employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) values (?, ?, ?, ?, ?, ?, ?, ?)<br>
Comments: Inserts a record in the employees table for employee number 1803.<br>
Result:</p>
<p class="has-line-data" data-line-start="59" data-line-end="61">Inserted -&gt; 1 record/s.<br>
Test Successful.</p>
<hr>
<p class="has-line-data" data-line-start="62" data-line-end="66">Method under test -&gt; public ResultSet getData(String sql)<br>
Query String: Select Count(employeeNumber) From employees<br>
Comments: Retrieves the total number of records in the employees table.<br>
Result:</p>
<p class="has-line-data" data-line-start="67" data-line-end="70">Count(employeeNumber)<br>
24<br>
Test Successful.</p>
<hr>
<p class="has-line-data" data-line-start="71" data-line-end="75">Method under test -&gt; public ResultSet getData(String sql, String dataType, String argument<br>
Query String: Select * From employees Where employeeNumber = 1803<br>
Comments: Retrieves the newly inserted record for employee number 1803.<br>
Result:</p>
<p class="has-line-data" data-line-start="76" data-line-end="79">employeeNumber                     lastName                           firstName                          extension                          email                              officeCode                         reportsTo                          jobTitle<br>
1803                               Boi                                Mushroom                           x1234                              <a href="mailto:mboi@classicmodelcars.com">mboi@classicmodelcars.com</a>          1                                  1002                               Security Officer<br>
Test Successful.</p>
<hr>
<p class="has-line-data" data-line-start="80" data-line-end="84">Method under test -&gt; updateData(String sql, String[] dataType, String[] argument)<br>
Query String: Delete From employees Where employeeNumber = 1803<br>
Comments: Deletes the newly inserted record for employee number 1803.<br>
Result:</p>
<p class="has-line-data" data-line-start="85" data-line-end="87">Deleted -&gt; 1 record/s.<br>
Test Successful.</p>
<hr>
<p class="has-line-data" data-line-start="88" data-line-end="92">Method under test -&gt; public ResultSet getData(String sql)<br>
Query String: Select Count(employeeNumber) From employees<br>
Comments: Retrieves the total number of records in the employees table.<br>
Result:</p>
<p class="has-line-data" data-line-start="93" data-line-end="96">Count(employeeNumber)<br>
23<br>
Test Successful.</p>
<hr>
<p class="has-line-data" data-line-start="97" data-line-end="101">Method under test -&gt; public void close()<br>
Query String: None<br>
Comments: Closes the connection to the MySQL local database.<br>
Result:</p>
<h2 class="code-line" data-line-start=102 data-line-end=104 ><a id="Test_Successfull_102"></a>Test Successful.
<p class="has-line-data" data-line-start="104" data-line-end="105">End of test.</p>
