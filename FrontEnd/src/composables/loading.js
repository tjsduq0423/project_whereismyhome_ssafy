import { useLoadingStore } from '@/stores/loading';
import { storeToRefs } from 'pinia';

export const useLoading = () => {
  const { loading } = storeToRefs(useLoadingStore());
  const { vLoading } = useLoadingStore();
  return {
    loading,
    vLoading,
  };
};
