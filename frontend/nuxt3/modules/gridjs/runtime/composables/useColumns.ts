import { html } from "gridjs";

export const measureColumns = [
  {
    id: "clientId",
    name: "ClientId",
    width: "30%",
    hidden: false,
    //formatter: (cell) => getNameHtml(cell),
  },
  {
    name: "Time",
    width: "30%",
    hidden: false,
  },
  {
    name: "Value",
    width: "30%",
    hidden: false,
  },
  // {
  //   name: "Color",
  //   width: "15%",
  //   hidden: false,
  //   formatter: (cell) => html(`<b class="has-text-primary">${cell}</b>`),
  // },
  // {
  //   name: "Picture",
  //   width: "35%",
  //   hidden: false,
  //   formatter: (cell) => html(`<img src="${cell}"/>`),
  // },
];

const getNameHtml = (cell) => {
  return html(`<b class="is-size-4">${cell}</b>`);
};
