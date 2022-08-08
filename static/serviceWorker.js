const cacheName = 'v1.0.0';

self.addEventListener('install', function(event) {
    self.skipWaiting();
    event.waitUntil(
        caches.open(cacheName).then(function(cache) {
            return cache.addAll([
                'assets/bec.mp3',
            ]);
        })
    );
});

self.addEventListener('fetch', (e) => {
    e.respondWith((async () => {
        return await fetch(e.request)
            .then(async (response) => {
                if (response.status === 200) {
                    const cache = await caches.open(cacheName);
                    // console.log(`[Service Worker] Caching: ${e.request.url}`);
                    cache.put(e.request, response.clone());
                }
                return response
            })
            .catch(async () => {
                const r = await caches.match(e.request);
                // console.log(`[Service Worker] Fetch from catch: ${e.request.url}`);
                if (r) { return r; }
            });
    })());
});
