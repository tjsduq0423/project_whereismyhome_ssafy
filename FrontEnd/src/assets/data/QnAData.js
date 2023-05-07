const data = [];
for (let i = 0; i < 64; i++) {
  data.push({
    id: i,
    isAnswered: false,
    title: `test${i}`,
    author: `author${i}`,
    createTime: `${i}시`,
    content: `testContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContenttestContent${i}`,
  });
}

export default data;
