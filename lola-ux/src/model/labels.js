export function serverLabelToClientLabel(serverLabel) {
  return {
    id: serverLabel.id,
    title: serverLabel.title,
    logo: serverLabel.logo,
    category: serverLabel.category,
    size: serverLabel.size,
    artists: serverLabel.artists
  };
}
