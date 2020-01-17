import React from "react";
import CardComponent from "./Card";
import { AppDataContext } from "../context";

export default function ProductListComponent() {
  const appData = React.useContext(AppDataContext);
  const labels = appData.labels;

  return (
    <div className="row">
      {labels === "loading" ? (
        "Record labels are loading, please wait..."
      ) : (
        <div>
          <p>Currently we manage {labels.length} record labels!</p>
          <div className="card-deck">
            {labels.map(label => {
              return (
                <div className="col-3 mx-auto my-3" key={label.id}>
                  <CardComponent label={label} />
                </div>
              );
            })}
          </div>
        </div>
      )}
    </div>
  );
}
