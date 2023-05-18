import { ref } from 'vue';
import { defineStore } from 'pinia';
import {
  getHouseMarkers,
  getSubwayMarkers,
  getSchoolMarkers,
  getHospitalMarkers,
  getCCTVMarkers,
  getBusMarkers,
} from '@/api/amen';

export const useMarkersStore = defineStore('markers', () => {
  const houseMarkers = ref([]);
  const schoolMarkers = ref([]);
  const cctvMarkers = ref([]);
  const hospitalMarkers = ref([]);
  const subwayMarkers = ref([]);
  const busMarkers = ref([]);

  const setHouseMarkers = async (lng, lat) => {
    try {
      const response = await getHouseMarkers(lng, lat);
      houseMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setSchoolMarkers = async (lng, lat) => {
    try {
      const response = await getSchoolMarkers(lng, lat);
      schoolMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setCCTVMarkers = async (lng, lat) => {
    try {
      const response = await getCCTVMarkers(lng, lat);
      cctvMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setHospitalMarkers = async (lng, lat) => {
    try {
      const response = await getHospitalMarkers(lng, lat);
      hospitalMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setSubwayMarkers = async (lng, lat) => {
    try {
      const response = await getSubwayMarkers(lng, lat);
      subwayMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };
  const setBusMarkers = async (lng, lat) => {
    try {
      const response = await getBusMarkers(lng, lat);
      busMarkers.value = [...response.data];
    } catch (err) {
      console.error(err);
    }
  };

  return {
    houseMarkers,
    schoolMarkers,
    cctvMarkers,
    hospitalMarkers,
    subwayMarkers,
    busMarkers,
    setHouseMarkers,
    setSubwayMarkers,
    setSchoolMarkers,
    setCCTVMarkers,
    setHospitalMarkers,
    setBusMarkers,
  };
});
