<template>
  <AppContent class="img">
    <template #header>
      <div class="cardOver"></div>
    </template>
    <template #default>
      <AppCardHeader class="py-3">Profile</AppCardHeader>
      <div class="container-fluid p-0">
        <div class="row mb-5 mx-0 p-0">
          <div class="col text-center">
            <h1 class="my-5">
              {{ userInfo.name }}
            </h1>
            <h5>userInfo.email</h5>
          </div>
        </div>

        <div class="row">
          <div class="col-auto">
            <RouterLink to="/myPage/myQnA">My QnA</RouterLink>
          </div>
          <div class="col-auto">
            <RouterLink to="/myPage/myBookMark">My BookMark</RouterLink>
          </div>
        </div>
        <div class="row">
          <div class="col p-0">
            <RouterView v-slot="{ Component }">
              <template v-if="Component">
                <Suspense>
                  <component :is="Component"></component>
                  <template #fallback> Loading... </template>
                </Suspense>
              </template>
            </RouterView>
          </div>
        </div>
      </div>
    </template>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/layouts/AppContent.vue';
import AppCardHeader from '@/components/layouts/AppCardHeader.vue';
import { useAuthStore } from '@/stores/auth';
import { storeToRefs } from 'pinia';

const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);
</script>

<style lang="scss" scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('/img/apartment04jpg.jpg');
}
.row {
  margin: 1rem 0rem 1.5rem 0rem;
}
a {
  font-weight: bold;
  text-decoration: none;
  color: black;
  padding-bottom: 0.66rem;
  font-size: 1.5rem;
  &:hover {
    border-bottom: 3px solid black;
  }
  &.router-link-active {
    border-bottom: 3px solid black;
  }
}
hr {
  margin: 1rem 0px 1rem 0px;
}
.cardOver {
  margin-top: 25vh;
  margin-bottom: 5vh;
}
</style>
