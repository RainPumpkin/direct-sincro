import { cancellableFetch } from "../Services/CancellableFetch"

if ('Notification' in window && Notification.permission != 'granted') {
  console.log('Ask user permission')
  Notification.requestPermission(status => {  
      console.log('Status:'+status)
  });
}

export const subscribeToPushNotification = async (user) =>{
  if (Notification.permission === 'granted') {
    navigator.serviceWorker.ready
    .then((registration) => {
        console.log('Push notification subscribed.');
      registration.pushManager.getSubscription()
      .then((subscription) => {
        if (subscription) {
          return subscription
        }
        //---to subscribe push notification using pushmanager---
        registration.pushManager.subscribe(
        //---always show notification when received---
        { userVisibleOnly: true , applicationServerKey: 'BIclYkmaTdoEO6e_jVORV9RH8bACZmVBe4iQCtQga_M4iBCUsICPoTXPs0cdFtutsoF-UEOg1Z11-Z4qd8ta52E'}
        )
        .then(function (subscription) {
            console.log('Push notification subscribed.');
            console.log(JSON.stringify(subscription));
            updateSubscriptionOnServer(user, subscription, false)
        })
        .catch(function (error) {
            
            console.error('Push notification subscription error: ', error);
        });
    })})
  }
}
  
export const unsubscribeFromPushNotification = async (user) => {
  if (Notification.permission === 'granted') {
  navigator.serviceWorker.ready
    .then(function(registration) {
        registration.pushManager.getSubscription()
        .then(function (subscription) {
            if(!subscription) {
                return;
            }
            subscription.unsubscribe()
            .then(function () {
                console.log('Push notification unsubscribed.');
                console.log(subscription);
                updateSubscriptionOnServer(user, subscription, true)
              })
            .catch(function (error) {
                console.error(error);
            });
        })
        .catch(function (error) {
            console.error('Failed to unsubscribe push ' +'notification.');
          });
    })
  }
}

const request = (uri, opts, dispatch) => {
  const fetch = cancellableFetch(uri, opts)
  fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok)})
  .catch((error) => { console.log(error) })
  return fetch.abort
}
async function updateSubscriptionOnServer(
  userId,
  subscription,
  unsubscribe
) {
  // Here's where you would send the subscription to the application server/database
  //if the action is subscribe. make POST request to the server with user's subscription data.
  try {
    if (!unsubscribe) {
      request(`/api/subscritores/${userId}/push`, { method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(subscription)})

      //if the action is unsubscribe. make DELETE request to remove user's subscription from database.
    } else {
      request(`/api/subscritores/${userId}/push`, { method: "DELETE", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(subscription)})
      console.log('User is not subscribed')
    }
  } catch (error) {
    console.log('Update Subscription failed', error)
  }
}