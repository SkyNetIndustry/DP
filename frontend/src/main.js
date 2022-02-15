import { createApp } from "vue";
import App from "./App.vue";

import router from "./router.js";
import store from "./store/index.js";

// https://stackoverflow.com/a/65555991 - bootstrap import
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

const app = createApp(App);

app.use(router).use(store).mount("#app");
