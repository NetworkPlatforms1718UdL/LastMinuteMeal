// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });


 // Import the Firebase SDK for Google Cloud Functions.
const functions = require('firebase-functions');
 // Import and initialize the Firebase Admin SDK.
const admin = require('firebase-admin');
admin.initializeApp();


exports.sendNotifications = functions.database.ref('/restaurant').onWrite((change, context) => {

  if (!change.after.val()) {
      return console.log('Message un-changed');
  }

  const text = change.after.val();

  var payload = {
    notification: {
      title: `New establishment in the app: `,
      body: 'There are new restaurants in our app, book you meal now!',
      icon: '/images/firebase_ic.png'
    }
  };

  var getDeviceTokensPromise = admin.database().ref(`/tokens`).once('value');

      let tokensSnapshot;

        // The array containing all the user's tokens.
      let tokens;

      return Promise.all([getDeviceTokensPromise]).then(results => {
           tokensSnapshot = results[0];

           // Check if there are any device tokens.
           if (!tokensSnapshot.hasChildren()) {
             return console.log('There are no notification tokens to send to.');
           }
           // Listing all tokens as an array.
           tokens = Object.keys(tokensSnapshot.val());


  return admin.messaging().sendToDevice(tokens, payload)
  });
});

//------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------


exports.sendConfirmation = functions.database.ref('/confirmation/{userId}').onCreate((snapshot, context) => {

  const userId = context.params.userId;

  console.log(userId);

  var payload = {
    notification: {
      title: `Your booking has been confirmed! `,
      body: 'The restaurant has accepted your booking, have a nice meal!',
      icon: '/images/firebase_ic.png'
    }
  };

  var getDeviceTokensPromise = admin.database().ref(`/users/`+userId).once('value');

      let tokensSnapshot;

        // The array containing all the user's tokens.
      let tokens;

      return Promise.all([getDeviceTokensPromise]).then(results => {
           tokensSnapshot = results[0];

           // Check if there are any device tokens.
           if (!tokensSnapshot.hasChildren()) {
             return console.log('There are no notification tokens to send to.');
           }
           // Listing all tokens as an array.
           tokens = Object.keys(tokensSnapshot.val());



  return admin.messaging().sendToDevice(tokens, payload)
  });
});