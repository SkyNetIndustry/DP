<template>
  <div class="container-fluid vh-100">
    <!-- TODO - v-for -->
    <div class="row row-cols-2 h-50" v-for="(stair, index) in stairs" :key="stair">

      <template v-if="index==0">
        <stair class="col-8 " @click="navigateto(stair.id)" :id="stair.id"></stair>
        <div class="col-4 "></div>
      </template>

      <template v-if="index==1">
          <div class="col-4 "></div>
          <stair class="col-8 " @click="navigateto(stair.id)" :id="stair.id"></stair>
      </template>
      
    </div>
  </div>
</template>

<script>
import Stair from "./Stair.vue";
import { StairService } from "@/common/api.service";

export default {
  components: {
    Stair,
  },

  data() {
    return {
      stairs: null,
    }
  },

  methods: {
    navigateto(id) {
      this.$router.push({ name: "stair", params: { id: id } });
    },

  },

  async created() {
    this.stairs = await StairService.getAllStairs();
    console.log("StairBackground.vue -> created:\n");
  }
};
</script>

<style scoped>
.stair {
  text-align: center;

  border: 1px solid;
  border-color: white;
  border-radius: 20%;
}
</style>
