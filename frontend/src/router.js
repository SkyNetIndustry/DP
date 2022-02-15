import { createRouter, createWebHistory } from "vue-router";

import StairBackground from "./pages/stair/StairBackground.vue";
import Stair from "./pages/stair/Stair.vue";
import NotFound from "./pages/NotFound.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", component: StairBackground },
    { path: "/stair/:id", component: Stair, name: "stair" , props: true},
    { path: "/:notFound(.*)", component: NotFound },
  ],
});

export default router;
