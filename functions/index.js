const functions = require('firebase-functions');


//import admin module
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.chatA = functions.firestore
    .document('Group-A/{userId}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:newValue.name,
            body: newValue.message,
            icon:newValue.uid,
            sound: "default"
            
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-A", payload, options);


      // perform desired operations ...
    });
exports.chatB = functions.firestore
    .document('Group-B/{userId}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:newValue.name,
            body: newValue.message,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-B", payload, options);


      // perform desired operations ...
    });
exports.chatC = functions.firestore
    .document('Group-C/{userId}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:newValue.name,
            body: newValue.message,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-C", payload, options);


      // perform desired operations ...
    });
exports.chatD = functions.firestore
    .document('Group-D/{userId}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:newValue.name,
            body: newValue.message,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-D", payload, options);


      // perform desired operations ...
    });
exports.chatE = functions.firestore
    .document('Group-E/{userId}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:newValue.name,
            body: newValue.message,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-E", payload, options);


      // perform desired operations ...
    });
exports.chatF = functions.firestore
    .document('Group-F/{userId}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:newValue.name,
            body: newValue.message,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-F", payload, options);


      // perform desired operations ...
    });
exports.chatG = functions.firestore
    .document('Group-G/{userId}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:newValue.name,
            body: newValue.message,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-G", payload, options);


      // perform desired operations ...
    });
exports.chatH = functions.firestore
    .document('Group-H/{userId}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:newValue.name,
            body: newValue.message,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-H", payload, options);


      // perform desired operations ...
    });
exports.chatI = functions.firestore
    .document('Group-I/{userId}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:newValue.name,
            body: newValue.message,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-I", payload, options);


      // perform desired operations ...
    });
exports.chatJ = functions.firestore
    .document('Group-J/{userId}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:newValue.name,
            body: newValue.message,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-J", payload, options);


      // perform desired operations ...
    });
exports.pollA = functions.firestore
    .document('polls/Group-A')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:"Bunk poll started!",
            body: newValue.details,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-APoll", payload, options);


      // perform desired operations ...
    });
exports.pollB = functions.firestore
    .document('polls/Group-B')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:"Bunk poll started!",
            body: newValue.details,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-BPoll", payload, options);


      // perform desired operations ...
    });
exports.pollC = functions.firestore
    .document('polls/Group-C')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
           title:"Bunk poll started!",
            body: newValue.details,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-CPoll", payload, options);


      // perform desired operations ...
    });
exports.pollD = functions.firestore
    .document('polls/Group-D')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:"Bunk poll started!",
            body: newValue.details,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-DPoll", payload, options);


      // perform desired operations ...
    });
exports.pollE = functions.firestore
    .document('polls/Group-E')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:"Bunk poll started!",
            body: newValue.details,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-EPoll", payload, options);


      // perform desired operations ...
    });
exports.pollF = functions.firestore
    .document('polls/Group-F')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:"Bunk poll started!",
            body: newValue.details,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-FPoll", payload, options);


      // perform desired operations ...
    });
exports.pollG = functions.firestore
    .document('polls/Group-G')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:"Bunk poll started!",
            body: newValue.details,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-GPoll", payload, options);


      // perform desired operations ...
    });
exports.pollH = functions.firestore
    .document('polls/Group-H')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:"Bunk poll started!",
            body: newValue.details,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-HPoll", payload, options);


      // perform desired operations ...
    });
exports.pollI = functions.firestore
    .document('polls/Group-I')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:"Bunk poll started!",
            body: newValue.details,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-IPoll", payload, options);


      // perform desired operations ...
    });
exports.pollJ = functions.firestore
    .document('polls/Group-J')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();

      
    const payload = {
        notification: {
            title:"Bunk poll started!",
            body: newValue.details,icon:newValue.uid,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("Group-JPoll", payload, options);


      // perform desired operations ...
    });

