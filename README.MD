This project is an example of how to use Reactive APIs with Spring WebFlux.

To complete the exercises in this example, perform the following steps:

1. To learn the basics of Reactive APIs, implement the TODO statements in the PersonController class.
2. To learn the basics of unit testing a Reactive API, implement the TODO statements in the PersonControllerTests class to verify the implementation in step 1 is correct.
3. Reference BookController and BookControllerTests for additional examples to help with the TODO statements.
4. Reference FluxAndMonoExampleTests for more examples.
5. (Optional) To get an idea of how the two types of APIs (WebFlux and MVC) compare, run concurrentRequests.js.
   1. Ensure that the ReactiveExample project is running on localhost:8080
   2. In a terminal window, navigate to src/testclient directory
   3. Run "npm install" (to install node_modules if not already present)
   4. Run "node concurrentRequests.js". If the script successfully executes, the results of the requests should populate the console.
   5. Observe the total time it takes between WebFlux and MVC.
   6. Modify the numberOfRequests variable in the concurrentRequests.js script to a lower or higher number and see how it behaves.
