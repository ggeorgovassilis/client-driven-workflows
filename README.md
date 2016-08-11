# client driven workflows

Java/Spring example project for the concepts presented in [Scalability through client-driven workflows](https://georgovassilis.blogspot.com/2016/08/scalability-through-client-driven.html).

The example code is a Java web application which demonstrates a simple expense claim form and consists of:
- HRService which authenticates users by email and password and returns a digitally signed document with the users credentials
- ERPService which accepts user credentials issued by HRService and returns a digitally signed document with the cost centers
the user can charge expenses to
- PayrollService which accepts an expense claim with the user credentials and cost center as an attachment and returns a digitally
signed document which contains the expense claim receipt
- a Spring aspect called DocumentSigningAspec which decorates the three aforementioned services and validates that all arguments
passed to them have valid signatures. It furthermore signs documents which the services return.
All services expose REST APIs.

The code is meant to run with the provided unit tests and not as a stand alone application in a servlet container.