export const parseDate = (dateStr) => {
  const date = new Date(dateStr);
  const year = String(date.getFullYear()).padStart(2, "0"); // 연도
  const month = String(date.getMonth() + 1).padStart(2, "0"); // 월 (0부터 시작하므로 1을 더해야 합니다)
  const day = String(date.getDate()).padStart(2, "0"); // 일
  const hours = String(date.getHours()).padStart(2, "0"); // 시
  const minutes = String(date.getMinutes()).padStart(2, "0"); // 분
  const seconds = String(date.getSeconds()).padStart(2, "0"); // 초
  return { year, month, day, hours, minutes, seconds };
};
