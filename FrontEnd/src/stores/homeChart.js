import { defineStore } from 'pinia';
import { getChartInfoBySidoName } from '@/api/info';
import { computed, ref } from 'vue';

export const useHomeChartStore = defineStore('homeChar', () => {
  const curSidoName = ref(null);
  const dataList = ref([]);
  const chartData = computed(() => {
    return dataList.value.find(item => item.name === curSidoName.value);
  });

  const setChartData = async sido => {
    const response = await getChartInfoBySidoName(sido);
    const data = {
      name: sido,
      labels: [...response.data.map(el => el.gugunName)],
      datasets: [
        //관심도 추가.
        {
          label: '거래량',
          backgroundColor: '#B799FF',
          data: [...response.data.map(el => el.dealAmount)],
        },
        {
          label: '조회수',
          backgroundColor: '#ACBCFF',
          data: [...response.data.map(el => el.viewCount)],
        },
        {
          label: '북마크',
          backgroundColor: '#AEE2FF',
          data: [...response.data.map(el => el.markCount)],
        },
      ],
    };
    dataList.value.push(data);
  };

  return { chartData, setChartData, dataList, curSidoName };
});
