import React from "react";
import { AppDataContext } from "../context";

export default function ArtistsList() {
  const appData = React.useContext(AppDataContext);
  const labels = appData.labels;
}
