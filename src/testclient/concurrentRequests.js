// Script to simulate sending concurrent requests to the different types of APIs
// Response times will vary based on the environment, but in general,
// you should see the nonblocking total time is less than the blocking total time
// Run this script using Node.js
// Commands:
// Ensure that the ReactiveExample project is running on localhost:8080
// Ensure you are in the testclient directory
// npm install (to install node_modules if not already present)
// node concurrentRequests.js

const axios = require('axios');

async function sendRequest(apiUrl) {
    try {
        const startTime = Date.now();
        const response = await axios.get(apiUrl);
        const endTime = Date.now();
        const duration = (endTime - startTime) / 1000;

        console.log(`[${apiUrl}] Response: ${response.status} - ${response.data} - Time: ${duration} s`);
        return duration;
    } catch (error) {
        console.error(`[${apiUrl}] Error: ${error.response.status} - ${error.response.data}`);
        return 0;
    }
}

// Number of concurrent requests to send
const numberOfRequests = 30;

(async () => {
    // Send concurrent requests to the Spring Web Blocking API
    const mvcPromises = [];
    for (let i = 0; i < numberOfRequests; i++) {
        mvcPromises.push(sendRequest('http://localhost:8080/api/blocking/slow'));
    }

    const mvcDurations = await Promise.all(mvcPromises);
    const mvcTotalTime = mvcDurations.reduce((acc, duration) => acc + duration, 0);
    console.log(`[Spring Web (Blocking)] Total Time: ${mvcTotalTime.toFixed(2)} s`);

    // Send concurrent requests to the Spring WebFlux API (Non-Blocking)
    const webfluxPromises = [];
    for (let i = 0; i < numberOfRequests; i++) {
        webfluxPromises.push(sendRequest('http://localhost:8080/api/nonblocking/slow'));
    }

    const webfluxDurations = await Promise.all(webfluxPromises);
    const webfluxTotalTime = webfluxDurations.reduce((acc, duration) => acc + duration, 0);
    console.log(`[Spring WebFlux (NonBlocking)] Total Time: ${webfluxTotalTime.toFixed(2)} s`);
})();
