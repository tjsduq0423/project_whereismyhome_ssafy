const data = [];
for (let i = 0; i < 10; i++) {
  data.push({
    id: i,
    title: `test${i}`,
    author: `author${i}`,
    createTime: `${i}시`,
  });
}

export default data;