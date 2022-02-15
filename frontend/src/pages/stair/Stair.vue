<template>
  <div class="row row-cols-3 mw-100 mh-100 m-0">
    <div v-if="sensors[0]" class="p-0 position-relative col">
      <h2 class="position-absolute top-50 start-100 translate-middle">{{sensors[0].value}}</h2>
      <img class="image position-absolute top-50 start-100 translate-middle" :src="require(`@/static/right_arrow.png`)">
    </div>

    <div class="p-0 position-relative col">
    </div>

    <div v-if="sensors[1]" class="p-0 position-relative col">
      <h2 class="position-absolute top-50 start-0 translate-middle">{{sensors[1].value}}</h2>
      <img class="image position-absolute top-50 start-0 translate-middle" :src="require(`@/static/left_arrow.png`)">
    </div>

    <div v-if="sensors[2]" class="p-0 position-relative col">
      <h2 class="pt-5 position-absolute top-50 start-50 translate-middle">{{sensors[2].value}}</h2>
      <img class="image position-absolute top-50 start-50 translate-middle" :src="require(`@/static/house.png`)">
    </div>

    <div class="p-0 position-relative col">
      <h2 class="position-absolute top-50 start-50 translate-middle">recuperation</h2>
      <img class="recuperation position-absolute top-50 start-50 translate-middle" :src="require(`@/static/recuperation.png`)">
    </div>

    <div v-if="sensors[3]" class="p-0 position-relative col">
      <h2 class="position-absolute top-50 start-50 translate-middle">{{sensors[3].value}}</h2>
      <img class="image position-absolute top-50 start-50 translate-middle" :src="require(`@/static/out.png`)">
    </div>

    <div v-if="sensors[4]" class="p-0 position-relative col">
      <h2 class="position-absolute top-50 start-100 translate-middle">{{sensors[4].value}}</h2>
      <img class="image position-absolute top-50 start-100 translate-middle" :src="require(`@/static/left_arrow.png`)">
    </div>

    <div class="p-0 position-relative col">
    </div>

    <div v-if="sensors[5]" class="p-0 position-relative col">
      <h2 class="position-absolute top-50 start-0 translate-middle">{{sensors[5].value}}</h2>
      <img class="image position-absolute top-50 start-0 translate-middle" :src="require(`@/static/right_arrow.png`)">
    </div>

    </div>
</template>

<script>
import { SensorService } from "@/common/api.service";

export default {
  props: ['id'],

  data() {
    return {
      sensors: [],
      interval: 0,
    }
  },

  methods: {
    async loadStair() {
      this.sensors = await SensorService.getSensor(this.id);
      console.log("Stair.vue -> loadStair() - ", this.id);
    }
        
  },

  created() {
     this.loadStair();
     console.log("Stair.vue -> created");
   },

  mounted: function () {
  this.interval = window.setInterval(() => {
    this.loadStair();
  }, 5000)},

  unmounted: function () {
    window.clearInterval(this.interval);
  } 
};
</script>

<style scoped>
.image {
  width: 50%!important;
}

.recuperation {
  width: 100%!important;
}
</style>
