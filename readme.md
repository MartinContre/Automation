# Gmail API Test Project 📧

This project is a testing project that subscribes to a newsletter, 
accesses Gmail, and follows the instructions received in the Gmail email. 
The subscription process is randomized.

## Prerequisites ⚙️

Before getting started, make sure you have completed the following steps:

1. Create a test account for Gmail.
2. Set up Google Cloud by following [these instructions](https://console.cloud.google.com/getting-started).

## Project Workflow 🛠️

The project essentially follows this workflow:

1. Open the [Euronews website](https://www.euronews.com/).
2. Navigate to the "Newsletters" link in the header, opening the "Newsletters" page.
3. Select a random newsletter subscription plan, revealing an email form at the bottom of the page.
4. Enter an email address and click the "Submit" button, resulting in a confirmation email.
5. Follow the link received from the email to a page confirming successful subscription.
6. Click "Back to the site" to return to the Euronews main page.
7. Navigate to the "Newsletters" link in the header again, select the same subscription plan as in step 3, and click "See preview" to view the plan.
8. On the preview page, locate and retrieve a link to unsubscribe from the mailing list, and follow this link in the browser.
9. Enter the email address and click the "Submit" button, resulting in a success message for subscription cancellation.
10. Ensure that you haven't received an email confirming the subscription cancellation.

## Test Case ✅

The project includes a test case that verifies this process:

```GmailApiTest.java
@Test
void test() {
    // Test code here...
}
```

## Configuration 🔧

config_data.json: Configure your Gmail email address.
credentials.json: Set your access_token and refresh_token.

## Usage 🚀

Configure the necessary files as mentioned above.
Execute the GmailApiTest class after completing all configurations.

## Author 👥
Martin Perez