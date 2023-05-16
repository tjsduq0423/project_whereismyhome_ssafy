import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';
import 'bootstrap-icons/font/bootstrap-icons.css';
import 'animate.css';

import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import router from './router';
import KakaoMapsPlugin from './plugins/kakaoMaps';

const app = createApp(App);

app.use(KakaoMapsPlugin);
app.use(createPinia());
app.use(router);

app.mount('#app');
