import React, { useState } from 'react';
import * as S from './style';

export default function PromisePage({ promise, curUser, winner, user1, user2 }) {
  const [uploadedFiles, setUploadedFiles] = useState([]);

  const handleFileChange = (event) => {
    const files = event.target.files;
    setUploadedFiles(files);
  };

  return (
    <S.Main>
      {promise}
      <input
        type="file"
        accept="*"
        id="input-file"
        onChange={handleFileChange}
      />
    </S.Main>
  );
}