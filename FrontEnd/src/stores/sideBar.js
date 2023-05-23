import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import { getAptInfoByCode, getAptRankByCode, getAptDealInfoByCode } from '@/api/info';
import {
  getBusDistance,
  getSubwayDistance,
  getSchoolDistance,
  getHospitalDistance,
} from '@/api/amen';
export const useSideBarStore = defineStore('sideBar', () => {
  // sidebar show
  const showSideBar = ref(false);
  // sidebar loadView latlng
  const lodaViewLatLng = ref([]);
  // sidebar info
  const aptToAmenDistanceInfo = ref({});
  const aptInfo = ref({});
  const aptDealInfo = ref([]);
  const aptRankInfo = ref('');
  const priceRange = computed(() => {
    const prices = aptDealInfo.value.map(info => parseInt(info.dealAmount.replace(',', '')));
    if (prices.length === 0) return [];
    return [Math.min(...prices), Math.max(...prices)];
  });
  const chartData = computed(() => {
    const result = [];
    const groupedData = {};
    aptDealInfo.value.forEach(item => {
      const key = `${item.dealYear}-${item.dealMonth}`;
      if (!groupedData[key]) {
        groupedData[key] = { count: 0, total: 0 };
      }
      groupedData[key].count++;
      groupedData[key].total += parseInt(item.dealAmount.replace(',', ''));
    });
    for (const key in groupedData) {
      const [year, month] = key.split('-');
      const average = groupedData[key].total / groupedData[key].count;
      result.push({ dealYear: parseInt(year), dealMonth: parseInt(month), average });
    }
    return {
      labels: [...result.map(e => `${e.dealYear}.${e.dealMonth}`)],
      datasets: [
        {
          label: '매매가(만원)',
          backgroundColor: '#f87979',
          data: [...result.map(e => e.average)],
        },
      ],
    };
  });

  const tableItems = computed(() => {
    const data = [...aptDealInfo.value];
    data.sort((a, b) => {
      const dateA = new Date(a.dealYear, a.dealMonth - 1, a.dealDay);
      const dateB = new Date(b.dealYear, b.dealMonth - 1, b.dealDay);
      return dateB - dateA;
    });
    return data.map(info => {
      return {
        date: `${info.dealYear}.${info.dealMonth}.${info.dealDay}`,
        dealAmount: parseInt(info.dealAmount.replace(',', '')),
        area: info.area,
        floor: info.floor,
      };
    });
  });

  const setAptToAmenDistanceInfo = async aptCode => {
    const bus = (await getBusDistance(aptCode)).data;
    const subway = (await getSubwayDistance(aptCode)).data;
    const school = (await getSchoolDistance(aptCode)).data;
    const hospital = (await getHospitalDistance(aptCode)).data;
    aptToAmenDistanceInfo.value = {
      학교: school,
      병원: hospital,
      버스: bus,
      열차: subway,
    };
  };

  const setAptInfo = async (aptCode, aptName) => {
    const response = await getAptInfoByCode(aptCode);
    aptInfo.value = { ...response.data, aptName };
  };
  const setAptDealInfo = async aptCode => {
    const response = await getAptDealInfoByCode(aptCode);
    aptDealInfo.value = [...response.data];
  };
  const setAptRankByCode = async aptCode => {
    const response = await getAptRankByCode(aptCode);
    aptRankInfo.value = response.data;
  };

  return {
    tableItems,
    chartData,
    priceRange,
    lodaViewLatLng,
    showSideBar,
    aptInfo,
    aptDealInfo,
    aptRankInfo,
    setAptInfo,
    setAptDealInfo,
    setAptRankByCode,
    setAptToAmenDistanceInfo,
    aptToAmenDistanceInfo,
  };
});
