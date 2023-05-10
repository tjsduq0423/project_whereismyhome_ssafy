import http from '@/api/http';

const PopularStock = async () => {
  const response = await http.get('/tag/PopularStock');
  return response.data;
};

const PopularIndustry = () => http.get('/tag/PopularIndustry');

const PopularAuthor = () => http.get('/tag/PopularAuthor');

export { PopularStock, PopularIndustry, PopularAuthor };
