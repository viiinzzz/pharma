export default defineNuxtPlugin(async (nuxtApp) => {
  const measures = useMeasures();
  const { data: value } = await useFetch('http://localhost:8080/measure/all')
  measures.value = value;
});
