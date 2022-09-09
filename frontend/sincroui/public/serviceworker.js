const CACHE_NAME = "version-1";
const urlsToCache = [ 'index.html', 'offline.html' ];

const self = this;

// Install SW
self.addEventListener('install', (event) => {
    event.waitUntil(
        caches.open(CACHE_NAME)
            .then((cache) => {
                console.log('Opened cache');

                return cache.addAll(urlsToCache);
            })
    )
});

// Listen for requests
self.addEventListener('fetch', (event) => {
    event.respondWith(
        caches.match(event.request)
            .then(() => {
                return fetch(event.request) 
                    .catch(() => caches.match('offline.html'))
            })
    )
});

// Activate the SW
self.addEventListener('activate', (event) => {
    const cacheWhitelist = [];
    cacheWhitelist.push(CACHE_NAME);

    event.waitUntil(
        caches.keys().then((cacheNames) => Promise.all(
            cacheNames.map((cacheName) => {
                if(!cacheWhitelist.includes(cacheName)) {
                    return caches.delete(cacheName);
                }
            })
        ))
            
    )
});

self.addEventListener('push', (event) => {
    let body
    if (event.data) {
      //You can set an original message by passing it on the event.
      body = event.data.text()
    } else {
      body = 'Default body'
    }
   
    const options = {
      body: body,
      icon: '/your icon image',
      vibrate: [100, 50, 100],
      data: {
        dateOfArrival: Date.now(),
        primaryKey: 1,
      },
    }
    event.waitUntil(
      self.registration.showNotification('Your Message Title',    
      options))
  })