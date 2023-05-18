const data = [];
for (let i = 0; i < 112; i++) {
  data.push({
    id: i,
    title: `test${i}`,
    author: `author${i}`,
    content: `testContenttestContenttestContenttestContenttestContenttestContenttestContent${i}`,
    createTime: `${i}시`,
  });
}

export default data;
