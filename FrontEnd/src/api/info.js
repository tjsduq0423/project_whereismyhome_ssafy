import { info } from '.';

export const getAptInfoByCode = aptCode => info.get(`/${aptCode}`);

export const getAptRankByCode = aptCode => info.get(`/rank/${aptCode}`);

export const getAptDealInfoByCode = aptCode => info.get(`/deal/${aptCode}`);

export const getChartInfoBySidoName = sidoName => info.get(`/chart/${sidoName}`);
