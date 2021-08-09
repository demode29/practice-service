import React from 'react';
import './TableRow.css';

const TableRow = (props) => {
  return (
    <tr className={props.isHeader ? "row-class" : ""}>
        <td>{props.currentId}</td>
        <td>{props.secondColumn}</td>
        <td>{props.thirdColumn}</td>
    </tr>
  );
};

export default TableRow;